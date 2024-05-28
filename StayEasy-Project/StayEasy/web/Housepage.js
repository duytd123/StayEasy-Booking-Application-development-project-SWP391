let formBtn = document.querySelector('#open-bill');
let loginForm = document.querySelector('.login-form-container');
let formClose = document.querySelector('#form-close');
let formBtns = document.querySelector('#open-bills');


formBtn.addEventListener('click', () => {
    loginForm.classList.add('active');
});

formClose.addEventListener('click', () => {
    loginForm.classList.remove('active');
});

formBtns.addEventListener('click', () => {
    loginForm.classList.add('active');
});

formClose.addEventListener('click', () => {
    loginForm.classList.remove('active');
});
