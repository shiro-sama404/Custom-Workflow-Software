const weekDays = ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'];

function setHoverEffects(calendar) {

    const days = calendar.children;

    for(let i = 8; i < days.length; i++){
            
        if(!days[i].classList.contains('inactive')){
            days[i].addEventListener('mouseenter', () => {
                days[i % 7].classList.add('active');
            });

            days[i].addEventListener('mouseleave', () => {
                days[i % 7].classList.remove('active');
            });
        }
    }
}

export function render(cachedData, currentYear, currentMonth){

    if(document.getElementById('calendar') === null){
        document.getElementById('table-container').innerHTML = '<div id="calendar"></div>';
    }

    const calendar = document.getElementById('calendar');

    let weekDay             = new Date(currentYear, currentMonth, 1).getDay();
    let currentMonthLastDay = new Date(currentYear, currentMonth + 1, 0).getDate();
    let prevMonthLastDay    = new Date(currentYear, currentMonth, 0).getDate();
    let prevMonthDayStart   = prevMonthLastDay - weekDay;
    let nextMonthLastDay    = 42 - currentMonthLastDay - (prevMonthLastDay - prevMonthDayStart);

    console.log(nextMonthLastDay)

    let table = '';

    for(let i = 0; i < 7; i++){
        table += `<div class="header"><p>${weekDays[i]}</p></div>`;
    }

    if(cachedData){
        console.log("cache não vazia");
    }else{

        // dias do mês anterior
        for(let i = prevMonthDayStart; i < prevMonthLastDay; i++){
            table += `<div class="inactive"><p>${i+1}</p></div>`
        }

        // dias do mês atual
        for(let i = 1; i <= currentMonthLastDay; i++){
            if(i%5 == 0){
                table += `<div class="single"><p>${i}</p><div></div></div>`
            }else if(i%13 == 0){
                table += `<div class="many"><p>${i}</p><div></div></div>`
            }else{
                table += `<div><p>${i}</p></div>`
            }
        }

        // dias do mês seguinte
        for(let i = 1; i <= nextMonthLastDay; i++){
            table += `<div class="inactive"><p>${i}</p></div>`
        }

        calendar.innerHTML = table;
    }

    setHoverEffects(calendar);
}