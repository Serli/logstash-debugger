function () {
    var app = angular.module('confStash', []);

    app.controller('langController', function(){

        this.text=lang.fr;

    });

    var lang = {
        fr: {
            subTitle: 'Testez votre configuration Logstash en ligne !',
            first: '1. Rentrez au moins une ligne de log',
            second: '2. Insérez votre fichier .conf',
            third: '3. Et voila le résultat !',
            or: 'OU'
        },

        en: {
            subTitle: 'Testez votre configuration Logstash en ligne !',
            first: '1. Rentrez au moins une ligne de log',
            second: '2. Insérez votre fichier .conf',
            third: '3. Et voila le résultat !',
            or: 'OU'
        }
    };

};