/*           Datas           */
const date = new Date();
const monthsNames = ["Janeiro","Fevereiro","Marco","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"];
const monthsMaxDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

const month = monthsNames[date.getMonth()];
var maxDays = monthsMaxDays[date.getMonth()];

/*           Organizações           */
var organizacoes = [{
    "id": 0,
    "nome": '',
    "abreviacao": '',
    "idsSubOrganizacoes": [0, 1]
}]

function SubOrganizacaoInvalida(organizacoes, idSob, idSub, max){

    if(organizacoes[idSob].id >= organizacoes[idSub].id || subOrganizacao.id >= max)
        return true;

    for(let i = idSub - 1; i >= 0; i--){
        for(let j = 0; j < organizacoes[i].subOrganizacoes.length; j++)
            if(organizacoes[i].subOrganizacoes[j].id == organizacoes[idSub].id)
                return true;
        
        return false;
    }
    
    return true;
}

function geraOrganizacoes(quantidade){

    organizacoes.pop();
    var organizacao;

    for(let i = 0; i < quantidade; i++){
        organizacao.id = i;
        organizacao.nome = "Organizacao " + i;
        organizacao.nome = "Org " + i;

        let qtdSub = Math.floor(Math.random() * (quantidade - i));
        let sub = 0;

        for(let j = 0; j < qtdSub; j++){

            do{
                sub = Math.floor(Math.random() * quantidade) + i;
            }while(SubOrganizacaoInvalida(organizacoes, i, sub, quantidade));
                
            organizacao.subOrganizacoes.push(sub);
        }
    }
    organizacoes.push(organizacao);
}

/*           Militares           */
const patentes = [
    'Recruta', 
    'Soldado',
    'Cabo',
    'Terceiro sargento',
    'Segundo sargento',
    'Primeiro sargento',
    'Subtenente',
    'Aspirante a oficial',
    'Segundo tenente',
    'Primeiro tenente',
    'Capitão',
    'Major',
    'Tenente-Coronel',
    'Coronel',
    'General de Brigada',
    'General de Divisão',
    'General de Exército',
    'Marechal'];

const habilitacoes = [
    'A', 
    'B',
    'AB',
    'C',
    'D',
    'E'];

var militares = [{

    "id": 0,
    "identidade": 0,
    "nomeComplemento": '',
    "nomeGuerra": '',
    "postoGraduacao": '',
    "qas": '',
    "dtNascimento": new Date('01/01/1900'),
    "dtPraca": new Date('01/01/1900'),
    "dtPromocao": new Date('01/01/1900'),
    "antiguidade": 0,
    "cfcCasCao": 0,
    "habilitacao": '',
    "ativo": true,
    "idOrganizacao": '',
}]

function geraMilitares(quantidade){
    
    militares.pop();
    var militar;

    for(let i = 1; i < quantidade+1; i++){
        militar.id = i;
        militar.identidade = i;
        militar.nomeComplemento = String.fromCharCode(i);
        militar.nomeGuerra = String.fromCharCode(i);
        militar.postoGraduacao = patentes[Math.floor(Math.random() * 10)]; // até primeiro tenente
        militar.qas = String.fromCharCode(i);
        militar.dtNascimento = new Date('01/01/1900');
        militar.dtPraca = new Date('01/01/1900');
        militar.dtPromocao = new Date('01/01/1900');
        militar.antiguidade = i;
        militar.cfcCasCao = [Math.floor(Math.random() * 2)];
        militar.habilitacao = habilitacoes[Math.floor(Math.random() * 7)];
        militar.ativo = true;
        militar.idOrganizacao = organizacoes[Math.floor(Math.random() * organizacoes.length)]
    }

    militares.push(militar);
}

geraOrganizacoes(10);
geraMilitares(20);