// ==============================
// 🎬 LIST.JS – Listagem de Análises e Filmes
// ==============================

$(document).ready(function () {

    // -------- Listar filmes se a tabela existir --------
    if ($('#filmesTable').length) {
        listarFilmes();
    }

    // -------- Listar análises se o container existir --------
    if ($('#analises-lista').length) {
        listarAnalises();
    }

    // -------- Autocomplete: carregar todos os títulos para busca --------
    if ($('#campo-busca').length) {
        $.ajax({
            url: '/api/filmes',
            method: 'GET',
            success: function (filmes) {
                const datalist = $('#sugestoes');
                datalist.empty(); // limpa sugestões antigas
                filmes.forEach(filme => {
                    datalist.append(`<option value="${filme.titulo}"></option>`);
                });
            },
            error: function () {
                console.error('Erro ao carregar sugestões de filmes.');
            }
        });
    }

    // -------- Busca de filme por título --------
    $('#botao-buscar').click(function () {
        const titulo = $('#campo-busca').val().trim();
        const mensagemErro = $('#mensagem-erro');

        if (titulo === "") {
            mensagemErro.text("Digite um título para buscar.");
            return;
        }

        fetch(`/api/filmes/busca?titulo=${encodeURIComponent(titulo)}`)
                .then(resp => {
                    if (!resp.ok)
                        throw new Error("Filme não encontrado");
                    return resp.json();
                })
                .then(filme => window.location.href = `/filmes/${filme.id}`)
                .catch(() => {
                    mensagemErro.text("Filme não encontrado.");
                });
    });

});

// ==============================
// Função: Listar filmes (tabela)
// ==============================
function listarFilmes() {
    $.ajax({
        url: '/api/filmes',
        method: 'GET',
        success: function (filmes) {
            const tbody = $('#filmesTable tbody');
            tbody.empty();

            filmes.forEach(filme => {
                tbody.append(`
                    <tr>
                        <td>${filme.id}</td>
                        <td>${filme.titulo}</td>
                        <td>${filme.sinopse}</td>
                        <td>${filme.genero}</td>
                        <td>${filme.anoLancamento}</td>
                        <td>
                            <a href="/edit-filme?id=${filme.id}">Editar</a>
                            <button onclick="deleteFilme(${filme.id})">Excluir</button>
                        </td>
                    </tr>
                `);
            });
        },
        error: function () {
            alert('Erro ao carregar filmes.');
        }
    });
}

// ==============================
// Função: Listar análises (estilo YouTube)
// ==============================
function listarAnalises() {
    $.ajax({
        url: '/api/analises',
        method: 'GET',
        success: function (analises) {
            const container = $('#analises-lista');
            container.empty(); // limpa comentários antigos

            analises.forEach(a => {
                // cria o card do comentário
                const card = $(`
                    <div class="analise-card">
                        <div class="analise-conteudo">
                            <div class="analise-meta">
                                <span class="analise-autor">${a.autor}</span>
                                <span class="analise-id">#${a.id}</span>
                            </div>
                            <p class="analise-comentario">${a.comentario}</p>
                            <button class="ler-mais">Ler mais</button>
                            <div class="analise-acoes">
                                <button class="botao-editar">Editar</button>
                                <button class="botao-excluir">Excluir</button>
                            </div>
                        </div>
                    </div>
                `);

                // -------- Ler mais / Ler menos --------
                card.find('.ler-mais').click(function () {
                    card.toggleClass('expanded');
                    $(this).text(card.hasClass('expanded') ? 'Ler menos' : 'Ler mais');
                });

                // -------- Excluir comentário --------
                card.find('.botao-excluir').click(() => deleteAnalise(a.id));

                // -------- Editar comentário --------
                card.find('.botao-editar').click(() => window.location.href = `/edit-analise?id=${a.id}`);

                container.append(card); // adiciona card no container
            });
        },
        error: function () {
            alert('Erro ao carregar análises.');
        }
    });
}

// ==============================
// Função: Excluir filme
// ==============================
function deleteFilme(id) {
    if (!confirm('Tem certeza que deseja excluir este filme?'))
        return;

    $.ajax({
        url: `/api/filmes/${id}`,
        method: 'DELETE',
        success: function () {
            alert('Filme excluído com sucesso!');
            listarFilmes();
        },
        error: function () {
            alert('Erro ao excluir filme.');
        }
    });
}

// ==============================
// Função: Excluir análise
// ==============================
function deleteAnalise(id) {
    if (!confirm('Tem certeza que deseja excluir esta análise?'))
        return;

    $.ajax({
        url: `/api/analises/${id}`,
        method: 'DELETE',
        success: function () {
            alert('Análise excluída com sucesso!');
            listarAnalises();
        },
        error: function () {
            alert('Erro ao excluir análise.');
        }
    });
}
