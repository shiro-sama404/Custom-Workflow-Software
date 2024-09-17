/*                                  Calendário                                  */
const month = document.getElementById("month");
const tableBody = document.getElementById("table-body");
var works = document.querySelectorAll(".table-content");

const months = ['JANEIRO', 'FEVEREIRO', 'MARÇO', 'ABRIL', 'MAIO', 'JUNHO', 'JULHO', 'AGOSTO', 'SETEMBRO', 'OUTUBRO', 'NOVEMBRO', 'DEZEMBRO'];
const servicos = ['', '', '', '', 'Guarda', 'Rancho', 'Oficial de Dia'];
const date = new Date();
var currentMonth = date.getMonth();
var currentYear = date.getFullYear();

function renderPreview() {

    tableBody.innerHTML = '<div id="days"></div>';
    month.children[1].innerHTML = `${months[currentMonth]} ${currentYear}`;

    const lastDayIndex = new Date(currentYear, currentMonth + 1, 0).getDate();
    const militares = 20;
    let days = "";

    // Renderizando os dias
    for(let i = 1; i <= lastDayIndex; i++){
        days += `<div class="day">${i}</div>`;
    }

    tableBody.children[0].innerHTML = days;

    // Renderizando os militares
    let rows = '';
    for(let i = 1; i <= militares; i++){

        let row = `<div class="table-row">`; 
        row += `<div class="table-fixed">Militar ${i}</div>`;

        for(let j = 1; j <= lastDayIndex; j++){
            row += `<div class="table-content">${servicos[Math.floor(Math.random() * 7)]}</div>`;
        }

        row += `</div>`;
        rows += row;
    }

    tableBody.innerHTML += rows;

    works = document.querySelectorAll(".table-content");

    for(let i = 0; i < works.length; i++){
        works[i].addEventListener('mouseenter', () => {
            tableBody.children[0].children[i%(lastDayIndex)].classList.add("active");
            tableBody.children[Math.floor(i / lastDayIndex)+1].children[0].classList.add("active");
        });

        works[i].addEventListener('mouseleave', () => {
            tableBody.children[0].children[i%(lastDayIndex)].classList.remove("active");
            tableBody.children[Math.floor(i / lastDayIndex)+1].children[0].classList.remove("active");
        });
    }


}

// botões
const prev = document.getElementById("prev-btn");
const next = document.getElementById("next-btn");

prev.addEventListener('click', () => {
    currentMonth--;

    if(currentMonth < 0){
        currentMonth = 11;
        currentYear--;
    }

    renderPreview();
});

next.addEventListener('click', () => {
    currentMonth++;

    if(currentMonth > 11){
        currentMonth = 0;
        currentYear++;
    }

    renderPreview();
});

renderPreview();

/*                                  Filtro                                  */
const displayFilter = document.getElementById('enable-filter');
const filterButton = document.getElementById('filter-button');
const filterBar = document.getElementById('filter-bar');

const iconDown = document.getElementById('arrow-down');
const iconUp = document.getElementById('arrow-up');

displayFilter.addEventListener('click', () => {

    if(filterBar.style.display == ''){
        filterBar.style.display = 'none';
    }

    if(filterBar.style.display != 'none'){
        filterBar.style.display = 'none';
        filterBar.style.visibility = 'hidden';

        iconDown.style.display = 'block';
        iconUp.style.display = 'none';
    }else{
        filterBar.style.display = 'flex';
        filterBar.style.visibility = 'visible';
        
        iconDown.style.display = 'none';
        iconUp.style.display = 'block'
    }
})