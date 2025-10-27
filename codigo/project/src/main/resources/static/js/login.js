const loginOption    = document.getElementById('login-option');
const passwordOption = document.getElementById('password-option');

const usernameIcon      = document.getElementById('username');
const emailIcon        = document.getElementById('email');
const showPasswordIcon = document.getElementById('show-password');
const hidePasswordIcon = document.getElementById('hide-password');

const loginField    = document.getElementById('user-id');
const passwordField = document.getElementById('password');

const loginBtn = document.getElementById('login-btn');

let loginWithUsaname = true;
let showPassword     = false;

function usernameToggle(){

    if(loginWithUsaname){
        usernameIcon.style.display = 'block';
        emailIcon.style.display    = 'none';
    }else{
        usernameIcon.style.display = 'none';
        emailIcon.style.display    = 'block';
    }

    loginWithUsaname = !loginWithUsaname;
}

function passwordToggle(){

    if(showPassword){
        showPasswordIcon.style.display = 'block';
        hidePasswordIcon.style.display = 'none';
        passwordField.type = 'text';
    }else{
        showPasswordIcon.style.display = 'none';
        hidePasswordIcon.style.display = 'block';
        passwordField.type = 'password';
    }

    showPassword = !showPassword;
}

loginOption.addEventListener('click', usernameToggle);
passwordOption.addEventListener('click', passwordToggle);

usernameToggle();
passwordToggle();