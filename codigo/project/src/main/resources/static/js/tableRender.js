const weekDays = ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'];

function adjustTableColumnWidths(table) {

    const colWidths = [];

    table.querySelectorAll('tr').forEach((row) => {
        row.querySelectorAll('th, td').forEach((cell, index) => {
            const width = cell.offsetWidth;
            colWidths[index] = Math.max(colWidths[index] || 0, width);
        });
    });

    table.querySelectorAll('tr').forEach((row) => {
        row.querySelectorAll('th, td').forEach((cell, index) => {
            cell.style.width = `${colWidths[index]}px`;
        });
    });
}

function setHoverEffects(table) {

    const cells   = table.querySelectorAll('td');
    const headers = table.querySelectorAll('th');
    const rows    = table.querySelectorAll('tr').length;
    const columns = headers.length;

    for(let i = 1; i < rows; i++){
        for(let j = 1; j < columns; j++){
            
            cells[((i-1)*columns)+j].addEventListener('mouseenter', () => {
                headers[j].classList.add('active');
                cells[(i-1)*columns].classList.add('active');
            });

            cells[((i-1)*columns)+j].addEventListener('mouseleave', () => {
                headers[j].classList.remove('active');
                cells[(i-1)*columns].classList.remove('active');
            });
        }
    }
}

export function render(cachedData, currentYear, currentMonth, filters){

    if(document.getElementById('preview') === null){
        document.getElementById('table-container').innerHTML = '<table id="table"><thead id="preview-header"><tr></tr></thead><tbody id="preview-body"></tbody></table>';
    }

    const previewHeader = document.getElementById('preview-header');
    const previewBody   = document.getElementById('preview-body');

    let rows    = filters.selectedEscalas.length;
    let columns = new Date(currentYear, currentMonth + 1, 0).getDate();
    let weekDay = new Date(currentYear, currentMonth, 1).getDay();
    let header  = '<tr><th><div>Escalas</div></th>';

    for(let i = 0; i < columns; i++){
        header += `<th>${i+1}<br>${weekDays[(weekDay+i)%7]}</th>`;
    }

    previewHeader.innerHTML = header + '</tr>';

    let body = '';

    if(cachedData){

        for(let i = 0; i < rows; i++){
            body += '<tr>';
            for(let j = 0; j < columns; j++){
                body += `<td>${i+j+1}</td>`
            }
            body += '</tr>';
        }

        previewBody.innerHTML = body;
    }else{

        for(let i = 0; i < 10; i++){
            body += `<tr><td>escala ${i+1}</td>`;
            for(let j = 0; j < columns; j++){
                if((i*columns)+j+1 != 32){
                    body += `<td><p>Nome de Guerra</P><p>OM</P></td>`;
                }else{
                    body += `<td><p>Nome de Guerra</p><p>Nome de Guerra Muito Grande</p><p>Nome de Guerra Muito Muito Muito Muito Grande</p><p>OM</p></td>`;
                }
            }
            body += '</tr>';
        }

        previewBody.innerHTML = body;
    }

    adjustTableColumnWidths(table);
    setHoverEffects(table)
}