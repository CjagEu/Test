function digitalClock() {
    var fecha = new Date();
    var horas = fecha.getHours() + '';
    var minutos = fecha.getMinutes() + '';
    var segundos = fecha.getSeconds() + '';
    var dia = fecha.getDay();

    if(horas.length < 2){
        horas = '0' + horas;
    }

    if(minutos.length < 2){
        minutos = '0' + minutos;
    }

    if(segundos.length < 2){
        segundos = '0' + segundos;
    }

    var diasSemana = ['Dom','Lun','Mar','Mie','Jue','Vie','Sab'];

    var clock = diasSemana[dia] + ' ' + horas + ':' + minutos + ':' + segundos;
    
    document.getElementById("clock").innerHTML = clock;
}

digitalClock();

setInterval(digitalClock, 1000);