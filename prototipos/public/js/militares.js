/*                                  Calendário                                  */
const tableBody = document.getElementById("table-body");

function render() {

    const militares = 20;

    let rows = '';
    for(let i = 1; i <= militares; i++){

        let row = `<div class="table-row">`; 
        row += `<div class="table-fixed">Militar ${i}</div>`;

        for(let j = 1; j < 11; j++){
            row += `<div class="table-content"></div>`;
        }

        row += `</div>`;
        rows += row;
    }

    tableBody.innerHTML += rows;

    const headers = document.querySelectorAll(".table-header");

    for(let row = 1; row <= militares; row++){

        for(let col = 0; col < headers.length; col++){
            tableBody.children[row].children[col].style.width = getComputedStyle(headers[col]).width;

            tableBody.children[row].children[col].addEventListener('mouseenter', () => {
                tableBody.children[0].children[col].classList.add("active");
                tableBody.children[row].children[0].classList.add("active");
            });

            tableBody.children[row].children[col].addEventListener('mouseleave', () => {
                tableBody.children[0].children[col].classList.remove("active");
                tableBody.children[row].children[0].classList.remove("active");
            });
        }
    }
}

render();

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