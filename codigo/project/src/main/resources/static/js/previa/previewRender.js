import * as tableRender    from '../tableRender.js';
import * as calendarRender from '../calendarRender.js';

let cachedFilters = null;
let cachedData    = null;

let currentMonth = new Date().getMonth();
let currentYear  = new Date().getFullYear();
const minMonthSearch = currentMonth;
const maxMonthSearch = currentMonth + 3;

const monthNames = ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'];
const currentMonthName = document.getElementById('month-name');

const prevBtn  = document.getElementById('prev-btn');
const nextBtn  = document.getElementById('next-btn');
let monthBlock = 'prev';

const filterBtn = document.getElementById('filter-btn');
const searchBtn = document.getElementById('search-btn');

let filters = {
    'selectedMilitares': [],
    'selectedEscalas'  : [],
    'startDate'        : new Date(currentYear, currentMonth + 1, 0).getDate(),
    'endDate'          : new Date(currentYear, currentMonth, 1).getDate()
}

const viewFormatBtn = document.getElementById('toggle-view-btn');
let viewFormat = 'table';

const updateView = (view) =>{

    if(view === 'table'){
        tableRender.render(cachedData, currentYear, currentMonth, filters);
        viewFormat = 'table';
        viewFormatBtn.children[0].style.display = 'block';
        viewFormatBtn.children[1].style.display = 'none';
    }else if (view === 'calendar'){
        calendarRender.render(cachedData, currentYear, currentMonth);
        viewFormat = 'calendar';
        viewFormatBtn.children[0].style.display = 'none';
        viewFormatBtn.children[1].style.display = 'block';
    }
}

const navMonth = (direction) =>{

    if(direction === 'prev' && currentMonth > minMonthSearch){

        if(currentMonth === maxMonthSearch){
            nextBtn.style.visibility = 'visible';
        }

        currentMonth -= 1;
        currentYear -= currentMonth == 12 ? 1 : 0;
        currentMonthName.innerHTML = monthNames[currentMonth % 12];
        
        if(currentMonth === minMonthSearch){
            prevBtn.style.visibility = 'hidden';
        }

        //updateData();
        updateView(viewFormat);

    }else if(direction === 'next' && currentMonth < maxMonthSearch){

        if(currentMonth === minMonthSearch){
            prevBtn.style.visibility = 'visible';
        }

        currentMonth += 1;
        currentYear += currentMonth == 0 ? 1 : 0;
        currentMonthName.innerHTML = monthNames[currentMonth % 12];

        if(currentMonth === maxMonthSearch){
            nextBtn.style.visibility = 'hidden';
        }

        //updateData();
        updateView(viewFormat);
    }

    console.log('currentMonth: ', currentMonth)
}

function validateFilters(){

    if (!filters.startDate || !filters.endDate) {
        alert("Selecione um intervalo de datas válido.");
        return false;
    } else if (previaFilterDTO.dataInicio > previaFilterDTO.dataFim) {
        alert("A data de início não pode ser posterior à data de fim.");
        return false;
    }

    return true;
}

async function updateData() {
    // Validação dos filtros
    if (!validateFilters()) {
        return;
    }

    // Verifica se os filtros atuais estão em cache
    if (JSON.stringify(filters) === JSON.stringify(cachedFilters)) {
        return;
    }

    // Prepara o objeto DTO para a requisição
    const previaFilterDTO = {
        idMilitar: filters.selectedMilitares,
        idEscala: filters.selectedEscalas,
        idUsuario: null,
        dataInicio: filters.startDate,
        dataFim: filters.endDate,
        status: null
    };

    // Atualiza os atributos de requisição do HTMX
    const visualizacaoContainer = document.getElementById("visualizacao-container");
    const urlParams = new URLSearchParams(previaFilterDTO).toString();
    visualizacaoContainer.setAttribute("hx-get", `/previa/render?${urlParams}`);
    visualizacaoContainer.setAttribute("hx-trigger", "load");

    // Usa HTMX para carregar os dados
    visualizacaoContainer.addEventListener("htmx:afterRequest", (event) => {
        // Atualiza o cache com os dados retornados
        cachedFilters = { ...filters };
        cachedData = event.detail.target.innerHTML;
    });

    // Dispara o evento do HTMX
    htmx.trigger(visualizacaoContainer, "load");
}

viewFormatBtn.addEventListener('click', () => updateView(viewFormat === 'table' ? 'calendar' : 'table'));

prevBtn.addEventListener('click', () => navMonth('prev'));
nextBtn.addEventListener('click', () => navMonth('next'));

currentMonthName.innerHTML = monthNames[currentMonth];
prevBtn.style.visibility = 'hidden';

updateView('table');