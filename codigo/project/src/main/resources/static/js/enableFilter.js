const openFilterBtn = document.getElementById('open-filter-btn');
const filterSection = document.getElementById('filter-section');
let enable = false;

function enableFilterSection(){

    if(enable){
        filterSection.style.display = 'block';
    }else{
        filterSection.style.display = 'none';
    }

    enable = !enable;
}

openFilterBtn.addEventListener('click', enableFilterSection);

enableFilterSection();