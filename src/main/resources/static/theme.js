// ------------------------------
// 🎨 Função para aplicar o tema
// ------------------------------
function applyTheme(theme) {
    document.body.className = theme; // remove classes antigas e aplica a nova

    // Atualiza texto do botão
    const btn = document.getElementById('botaoAlternarTema');
    if (btn) {
        btn.innerText = theme === 'dark' ? 'Modo Claro' : 'Modo Escuro';
    }
}

// ------------------------------
// 🎬 Alternar tema ao clicar
// ------------------------------
function toggleTheme() {
    const newTheme = document.body.classList.contains('dark') ? 'light' : 'dark';
    applyTheme(newTheme);

    // Salva preferência (opcional)
    fetch(`/set-theme?theme=${newTheme}`).catch(() => {
        console.warn('Não foi possível salvar o tema.');
    });
}

// ------------------------------
// Inicializa tema ao carregar a página
// ------------------------------
document.addEventListener('DOMContentLoaded', () => {
    // Lê cookie ou usa 'light' por padrão
    const theme = document.cookie.split(';').find(c => c.trim().startsWith('theme='))?.split('=')[1] || 'light';
    applyTheme(theme);
});
