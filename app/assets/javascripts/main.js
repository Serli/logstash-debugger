var XHR2Uploader = {
    xhr: new XMLHttpRequest(),
    text:null,
    file:null,
    addNewFile: function(theFile){
        this.file = theFile;
        if (this.text===null){
        }else{
            this.uploadFile();
        }
    },
    uploadFile: function(){

        if (this.file===null){
        }else {
            var progress = document.getElementById('uploadProgress');
            progress.max = this.file.size;

            if (XHR2Uploader.xhr.readyState === 0 || XHR2Uploader.xhr.readyState === 4) {
                XHR2Uploader.startUpload();
                console.log('upload');
            }
        }
    },
    startUpload: function(){
        XHR2Uploader.xhr.open('POST', '/submit');
        
        var formData = new FormData();
        formData.append('fileUpload',this.file);
        
        XHR2Uploader.xhr.send(formData);
        console.log('sent');
    },

    onUploading: function(e){
        var progress = document.getElementById('uploadProgress');
        progress.value = (e.loaded/ e.total);
    },

    onUploaded:function(e) {
        var progress = document.getElementById('uploadProgress');
        progress.value = e.loaded;
    }
};

XHR2Uploader.xhr.upload.onprogress = XHR2Uploader.onUploading;
XHR2Uploader.xhr.onload = XHR2Uploader.onUploaded;

function init(){
    document.getElementById('fileUpload').onchange = function(){
        XHR2Uploader.addNewFile(this.files[0]);
        console.log('changed');
    };
    settingDragNDrop();
}

function settingDragNDrop(){
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
            document.getElementById('fileUpload').className='form-control';
            XHR2Uploader.addNewFile(e.dataTransfer.files[0]);
            alert(""+XHR2Uploader.xhr.readyState);
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