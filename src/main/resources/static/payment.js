// payment.js

// URL-dən appointment məlumatlarını oxumaq
function getQueryParams() {
    const params = {};
    location.search.substr(1).split("&").forEach(pair => {
        const [key, value] = pair.split("=");
        params[decodeURIComponent(key)] = decodeURIComponent(value || "");
    });
    return params;
}

// Parametrləri oxu
const urlParams = getQueryParams();

const appointmentData = {
    purpose: urlParams["purpose"] || "",
    appointmentDate: urlParams["appointmentDate"] || "",
    appointmentTime: urlParams["time"] || "",
    patient: {
        firstName: urlParams["firstName"] || "",
        lastName: urlParams["lastName"] || "",
        fatherName: urlParams["fatherName"] || "",
        gender: urlParams["gender"] || "",
        age: parseInt(urlParams["age"]) || 0,
        mobileNumber: urlParams["mobile"] || ""
    },
    doctorId: parseInt(urlParams["doctor"]) || 0
};

// Input-lar
const form = document.getElementById('paymentForm');
const cvvInput = document.getElementById('cvv');
const cardInput = document.getElementById('cardNumber');
const expiryInput = document.getElementById('expiryDate');
const errorDiv = document.getElementById('errorMessage');

// Formatlama
expiryInput.addEventListener('input', () => {
    let value = expiryInput.value.replace(/\D/g, '');
    if (value.length > 2) {
        value = value.slice(0, 2) + '/' + value.slice(2, 4);
    }
    expiryInput.value = value;
});

cardInput.addEventListener('input', () => {
    let val = cardInput.value.replace(/\D/g, '').slice(0, 16);
    let newVal = '';
    for (let i = 0; i < val.length; i++) {
        if (i > 0 && i % 4 === 0) newVal += ' ';
        newVal += val[i];
    }
    cardInput.value = newVal;
});

cvvInput.addEventListener('input', () => {
    cvvInput.value = cvvInput.value.replace(/\D/g, '').slice(0, 3);
});

// Form submit
form.addEventListener('submit', async (e) => {
    e.preventDefault();

    errorDiv.style.display = 'none';
    errorDiv.textContent = '';

    const cardNumber = cardInput.value.replace(/\s/g, '');
    if (cardNumber.length !== 16 || !/^\d{16}$/.test(cardNumber)) {
        errorDiv.textContent = "Zəhmət olmasa, düzgün 16 rəqəmli kart nömrəsi daxil edin.";
        errorDiv.style.display = 'block';
        return;
    }

    if (cvvInput.value !== "545") {
        window.location.href = "failed.html";
        return;
    }

    try {
        const response = await fetch("/clinic-booking-ms/api/appointments", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(appointmentData)
        });

        if (response.ok) {
            window.location.href = "success.html";
        } else {
            window.location.href = "failed.html";
        }
    } catch (error) {
        console.error("Xəta:", error);
        window.location.href = "failed.html";
    }
});
