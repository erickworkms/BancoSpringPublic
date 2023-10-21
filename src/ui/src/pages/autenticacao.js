
const TOKEN_KEY = 'SECRET_KEY';

export const adicionaAutorizacao = (token) => {
    localStorage.setItem(TOKEN_KEY, token);
};

export const recebeAutorizacao = () => {
    return localStorage.getItem(TOKEN_KEY);
};

export const removeToken = () => {
    localStorage.removeItem(TOKEN_KEY);
};