let cachedFilters = null;
let cachedData    = null;

const firstBtn  = document.getElementById('first-page-btn');
const lastBtn   = document.getElementById('last-page-btn');
const prevBtn   = document.getElementById('prev-page-btn');
const nextBtn   = document.getElementById('next-page-btn');

const filterBtn = document.getElementById('filter-btn');
const searchBtn = document.getElementById('search-btn');

let filters = {
    'militares'      : [],
    'postosGraducao' : [],
    'secao'          : [],
    'qas'            : [],
    'curso'          : []
}

const viewFormatBtn = document.getElementById('toggle-view-btn');
let viewFormat = 'table';

function loadMilitary(data){

    if(data){

    }else{

        const militaryContainer = document.getElementById('military-container');

        let military = '';

        for(let i = 0; i < 10; i++){
            military += '<div><p>Nome</p><p>Posto</p><p>Subseção</p><p>QAS</p><p>curso</p></div>'
        }

        militaryContainer.innerHTML = military;
    }
}

function renderMilitary(){

}

loadMilitary(null);