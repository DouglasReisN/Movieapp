$(document).ready(function () {
    $('#formAdicionarFilme').submit(function (event) {
        event.preventDefault();

        const titulo = $('#titulo').val();
        const sinopse = $('#sinopse').val();
        const genero = $('#genero').val();
        const anoLancamento = $('#anoLancamento').val();

        $.ajax({
            url: '/api/filmes',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                titulo: titulo,
                sinopse: sinopse,
                genero: genero,
                anoLancamento: anoLancamento
            }),
            success: function () {
                alert('Filme adicionado com sucesso!');
                window.location.href = '/filmes';
            },
            error: function () {
                alert('Erro ao adicionar filme.');
            }
        });
    });
});
