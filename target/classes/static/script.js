document.addEventListener("DOMContentLoaded", () => {
    const today = new Date().toISOString().split('T')[0];
    document.getElementById("appointmentDate").min = today;
    const clinicSelect = document.getElementById("clinic");
    const sectorSelect = document.getElementById("sector");
    const doctorSelect = document.getElementById("doctor");
    const timeSelect = document.getElementById("time");

    function populateTimes() {
        timeSelect.innerHTML = "";
        let start = 9;
        while (start <= 17) {
            const hour = Math.floor(start);
            const minute = (start % 1 === 0.5) ? "30" : "00";
            const option = document.createElement("option");
            option.value = `${hour.toString().padStart(2, '0')}:${minute}`;
            option.text = `${hour.toString().padStart(2, '0')}:${minute}`;
            timeSelect.appendChild(option);
            start += 0.5;
        }
    }
    populateTimes();

    fetch("/clinic-booking-ms/clinics")
        .then(res => res.json())
        .then(data => {
            clinicSelect.innerHTML = `<option disabled selected>Seçin</option>`;
            data.forEach(clinic => {
                const opt = document.createElement("option");
                opt.value = clinic.id;
                opt.text = clinic.name;
                clinicSelect.appendChild(opt);
            });
        });

    clinicSelect.addEventListener("change", () => {
        const clinicId = clinicSelect.value;
        console.log("Seçilmiş klinika id:", clinicId);
        fetch("http://localhost:8080/clinic-booking-ms/sectors/" + clinicId)
            .then(res => {
                console.log("Sektorlar status:", res.status);
                return res.json();
            })
            .then(data => {
                console.log("Sektorlar data:", data);
                sectorSelect.innerHTML = `<option disabled selected>Seçin</option>`;
                data.forEach(sector => {
                    const opt = document.createElement("option");
                    opt.value = sector.id;
                    opt.text = sector.name;
                    sectorSelect.appendChild(opt);
                });
            })
            .catch(err => {
                console.error("Sektorlar üçün xəta:", err);
            });
    });

    sectorSelect.addEventListener("change", () => {
        const sectorId = sectorSelect.value;
        console.log("Seçilmiş sektor id:", sectorId);
        fetch("/clinic-booking-ms/doctors/" + sectorId)
            .then(res => {
                console.log("Doktorlar status:", res.status);
                return res.json();
            })
            .then(data => {
                console.log("Doktorlar data:", data);
                doctorSelect.innerHTML = `<option disabled selected>Seçin</option>`;
                data.forEach(doctor => {
                    const opt = document.createElement("option");
                    opt.value = doctor.id;
                    opt.text = doctor.fullName;
                    doctorSelect.appendChild(opt);
                });
            })
            .catch(err => {
                console.error("Doktorlar üçün xəta:", err);
            });
    });
});
