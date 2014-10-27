var dropZone = document.getElementById("dropBox");

dropZone.ondragover = function(e){
    e.preventDefault();
    console.log('dragover');
}

dropZone.ondrop = function(e){
    e.preventDefault();
    
    if ('files' in e.dataTransfer){
        dropZone.innerHTML = e.dataTransfer.files[0].name;
    }else{
        dropZone.innerHTML = '<p>Ce navigateur ne gère pas le drag\'n drop</p>';
    }
}