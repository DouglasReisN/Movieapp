$(document).ready(function () {
    $('#addAnaliseForm').submit(function (event) {
        event.preventDefault();

        const autor = $('#autor').val();
        const comentario = $('#comentario').val();

        $.ajax({
            url: '/api/analises',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                autor: autor,
                comentario: comentario
            }),
            success: function () {
                alert('Análise adicionada com sucesso!');
                window.location.href = '/analises'; // 🔥 Ajustado para Spring
            },
            error: function () {
                alert('Erro ao adicionar análise.');
            }
        });
    });
});
