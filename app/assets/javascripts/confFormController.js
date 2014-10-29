var app = angular.module('confStash', []);

app.controller('confStashController', function(){
    
    this.confForm= {
        input: null,
        confFile: XHR2Uploader.file,
    };

    this.isDisabled = function(){
        return (this.confForm.input === null);
    };
    
});