
const passwordInput = document.getElementById('password-input');
const strengthBar = document.getElementById('strength-bar');
const strengthText = document.getElementById('password-strength-text');
const recommendationsList = document.getElementById('recommendations');

const lengthRec = document.getElementById('length');
const lowercaseRec = document.getElementById('lowercase');
const uppercaseRec = document.getElementById('uppercase');
const numberRec = document.getElementById('number');
const symbolRec = document.getElementById('symbol');

passwordInput.addEventListener('input', () => {
    const password = passwordInput.value;
    const strength = calculatePasswordStrength(password);
    updateStrengthMeter(strength);
    updateRecommendations(password);
});

function calculatePasswordStrength(password) {
    let strength = 0;
    const checks = {
        length: password.length >= 8,
        lowercase: /[a-z]/.test(password),
        uppercase: /[A-Z]/.test(password),
        number: /[0-9]/.test(password),
        symbol: /[!@#$%^&*]/.test(password)
    };

    for (const key in checks) {
        if (checks[key]) {
            strength += 20;
        }
    }
    return strength;
}

function updateStrengthMeter(strength) {
    strengthBar.style.width = strength + '%';

    let strengthLabel = 'Muito Fraca';
    let color = '#ff4d4f'; // Vermelho

    if (strength >= 40) {
        strengthLabel = 'Fraca';
        color = '#ffa940'; 
    }
    if (strength >= 60) {
        strengthLabel = 'Média';
        color = '#ffd43b'; 
    }
    if (strength >= 80) {
        strengthLabel = 'Forte';
        color = '#74b816'; 
    }
    if (strength === 100) {
        strengthLabel = 'Muito Forte';
        color = '#2b8a3e'; 
    }

    strengthBar.style.backgroundColor = color;
    strengthText.textContent = strengthLabel;
}

function updateRecommendations(password) {
    const checks = {
        length: password.length >= 8,
        lowercase: /[a-z]/.test(password),
        uppercase: /[A-Z]/.test(password),
        number: /[0-9]/.test(password),
        symbol: /[!@#$%^&*]/.test(password)
    };

    updateRecItem(lengthRec, checks.length);
    updateRecItem(lowercaseRec, checks.lowercase);
    updateRecItem(uppercaseRec, checks.uppercase);
    updateRecItem(numberRec, checks.number);
    updateRecItem(symbolRec, checks.symbol);
}

function updateRecItem(element, isValid) {
    if (isValid) {
        element.classList.add('valid');
    } else {
        element.classList.remove('valid');
    }
}