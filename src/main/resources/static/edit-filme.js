
$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const filmeId = urlParams.get('id');

    // ✅ Verificar se o ID foi informado
    if (!filmeId) {
        alert('ID do filme não informado na URL.');
        window.location.href = '/filmes';
        return;
    }

    // 🔄 Carregar dados do filme
    $.ajax({
        url: `/api/filmes/${filmeId}`,
        method: 'GET',
        success: function (data) {
            $('#titulo').val(data.titulo);
            $('#sinopse').val(data.sinopse);
            $('#genero').val(data.genero);
            $('#anoLancamento').val(data.anoLancamento);
        },
        error: function () {
            alert('Erro ao carregar dados do filme. Verifique se o ID é válido.');
            window.location.href = '/filmes';
        }
    });

    // 🔄 Atualizar filme
    $('#editFilmeForm').submit(function (event) {
        event.preventDefault();

        const titulo = $('#titulo').val().trim();
        const sinopse = $('#sinopse').val().trim();
        const genero = $('#genero').val().trim();
        const anoLancamento = $('#anoLancamento').val().trim();

        // ✅ Validação dos campos
        if (!titulo || !sinopse || !genero || !anoLancamento) {
            alert('Por favor, preencha todos os campos.');
            return;
        }

        // ✅ Validação do ano
        if (isNaN(anoLancamento) || anoLancamento.length !== 4 || parseInt(anoLancamento) < 1800) {
            alert('Informe um ano de lançamento válido (ex: 2024).');
            return;
        }

        // 🔥 Enviar atualização
        $.ajax({
            url: `/api/filmes/${filmeId}`,
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify({
                titulo: titulo,
                sinopse: sinopse,
                genero: genero,
                anoLancamento: parseInt(anoLancamento)
            }),
            success: function () {
                alert('Filme atualizado com sucesso!');
                window.location.href = '/filmes';
            },
            error: function () {
                alert('Erro ao atualizar filme. Tente novamente.');
            }
        });
    });
});
