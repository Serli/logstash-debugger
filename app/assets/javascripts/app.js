var app = angular.module('confStash', []);

app.controller('confStashController', function(){

    this.confForm= {
        input: null,
        confFile: XHR2Uploader.file
    };

    this.isDisabled = function(){
        return (this.confForm.input === null);
    };

});

app.controller('langController', function(){

    this.text=lang.fr;

    this.changeLang = function(lang){
        if (lang===1){
            this.text=lang.fr;
        }else if(lang===2){
            this.text=lang.en;
        }
    };

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
        subTitle: 'Test your Logstash configuration online !',
        first: '1. Enter at least one line of log',
        second: '2. Insert your .conf file',
        third: '3. And here\'s the result !',
        or: 'OR'
    }
};