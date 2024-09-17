const menuSideBar = document.getElementById('menu');
const menuIcon = document.getElementById('menu-icon');
const mainContent = document.getElementsByTagName('main')[0];

menuIcon.addEventListener('click', () => {

    if(menuSideBar.style.width == '')
        menuSideBar.style.width = '10vw'

    console.log('entrou', menuSideBar.style.width);
    
    if(menuSideBar.style.width === '10vw'){
        menuSideBar.querySelector('ul').style.display = 'none';
        menuSideBar.querySelector('ul').style.visibility = 'hidden';
        menuSideBar.style.width = '5vw';
        mainContent.style.width = '95vw';
    }else{
        menuSideBar.querySelector('ul').style.display = 'flex';
        menuSideBar.querySelector('ul').style.visibility = 'visible';
        menuSideBar.style.width = '10vw';
        mainContent.style.width = '90vw';
    }
})