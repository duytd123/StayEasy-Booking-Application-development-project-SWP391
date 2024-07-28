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
document.addEventListener('DOMContentLoaded', () => {
    const readMoreBtns = document.querySelectorAll('.read-more-btn');
    
    readMoreBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            const description = btn.previousElementSibling;
            description.classList.toggle('full-description');
            if (description.classList.contains('full-description')) {
                btn.textContent = 'Read less';
            } else {
                btn.textContent = 'Read more';
            }
        });
    });
});

