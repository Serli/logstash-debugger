var XHR2Uploader = {
    xhr: new XMLHttpRequest(),
    file:null,
    addNewFile: function(theFile){
        file = theFile;
        var progressBar = document.createElement('PROGRESS');
        progressBar.id = file.name;
        progressBar.max=file.size;
        progressBar.value=0;
        insertAfter(progressBar,document.getElementById('fileUpload'));
        if(XHR2Uploader.xhr.readyState===0){
            XHR2Uploader.startUpload();
            console.log('upload');
        }
    },
    startUpload: function(){
        XHR2Uploader.xhr.open('POST', '/');
        
        var formData = new FormData();
        formData.append('fileUpload',file);
        
        XHR2Uploader.xhr.send(formData);
    },
    onUploading:function(e){
        var progressBar = document.getElementById(file.name);
        progressBar.value = e.loaded;
        progressBar.innerHTML = Math.round((progressBar.value/progressBar.max)*100)+'%';
    },
    onUploaded:function(e){
        var progressBar = document.getElementById(file.name);
        progressBar.value = file.size;
        progressBar.innerHTML = 'Upload terminé';
    }
};

XHR2Uploader.xhr.upload.onprogress = XHR2Uploader.onUploading;
XHR2Uploader.xhr.onload = XHR2Uploader.onUploaded;

function init(){
    document.getElementById('fileUpload').onchange = function(){
        XHR2Uploader.addNewFile(this.files[0]);
        console.log('changed');
    };
    settingDND();
}

function settingDND(){
    var dropZone = document.getElementById("dropBox");

    dropZone.ondragover = function(e){
        e.dataTransfer.effectAllowed = 'copy';
        e.dataTransfer.dropEffect = 'copy';
        e.preventDefault();
        console.log('dragover');
    };

    dropZone.ondrop = function(e){
        e.preventDefault();
        if ('files' in e.dataTransfer){
            XHR2Uploader.addNewFile(e.dataTransfer.files[0]);
        }else{
            dropZone.innerHTML = '<p>Ce navigateur ne gère pas le drag\'n drop</p>';
        }
    };
}

function insertAfter(newElement, afterElement) {
    var parent = afterElement.parentNode;
	
    if (parent.lastChild === afterElement) {
        parent.appendChild(newElement);
    } else {
        parent.insertBefore(newElement, afterElement.nextSibling);
    }
}