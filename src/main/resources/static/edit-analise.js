$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const analiseId = urlParams.get('id');

    // ✅ Verificar se o ID está presente
    if (!analiseId) {
        alert("ID da análise não informado na URL.");
        window.location.href = "/analises";
        return;
    }

    // 🔄 Carregar os dados da análise
    $.ajax({
        url: `/api/analises/${analiseId}`,
        method: 'GET',
        success: function (data) {
            $('#autor').val(data.autor);
            $('#comentario').val(data.comentario);
        },
        error: function () {
            alert('Erro ao carregar dados da análise. Verifique se o ID é válido.');
            window.location.href = "/analises";
        }
    });

    // 🔄 Enviar atualização da análise
    $('#editAnaliseForm').submit(function (event) {
        event.preventDefault();

        const autor = $('#autor').val();
        const comentario = $('#comentario').val();

        $.ajax({
            url: `/api/analises/${analiseId}`,
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify({
                autor: autor,
                comentario: comentario
            }),
            success: function () {
                alert('Análise atualizada com sucesso!');
                window.location.href = "/analises";
            },
            error: function () {
                alert('Erro ao atualizar análise.');
            }
        });
    });
});
