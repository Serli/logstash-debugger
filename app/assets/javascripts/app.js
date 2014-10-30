var app = angular.module('confStash', []);

app.controller('confStashController', function(){

    this.input=null;

    this.submitEnabled=function(){
        return (this.input===null || XHR2Uploader.file === null);
    };

    this.changeXHRTxt = function(){
        XHR2Uploader.text=this.input;
        this.submitEnabled();
    };

    this.submit = function(){
        XHR2Uploader.uploadFile();
    };

});

app.controller('langController', function(){

    this.text=lang.fr;

    this.changeLang = function(e){
        if (e===1){
            this.text=lang.fr;
        }else if(e===2){
            this.text=lang.en;
        }
    };

});

var lang = {
    fr: {
        subTitle: 'Testez votre configuration Logstash en ligne !',
        first: '1. Rentrez (au moins) une ligne de log',
        second: '2. Insérez votre fichier .conf',
        third: '3. Contenu du fichier',
        fourth: '4. Et voila le résultat !',
        or: 'OU'
    },

    en: {
        subTitle: 'Test your Logstash configuration online !',
        first: '1. Enter at least one log line',
        second: '2. Insert your .conf file',
        third: '3. File\'s content',
        fourth: '4. Here is the result !',
        or: 'OR'
    }
};