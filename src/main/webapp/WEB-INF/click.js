var table = document.getElementById('content');
var editingTd;
let temp;
table.onclick = function(event) {

    target = event.target;

    while (target != table) {
        if (target.className == 'edit-cancel') {
            finishTdEdit(editingTd.elem, false);
            return;
        }

        if (target.className == 'edit-ok') {
            finishTdEdit(editingTd.elem, true);

            return;
        }

        if (target.className == 'editable') {
            if (editingTd) return; // already editing
            makeTdEditable(target);
            temp = target;
            return;
        }
        //

        target = target.parentNode;
    }
}

function makeTdEditable(td) {
    editingTd = {
        elem: td,
        data: td.innerHTML
    };

    td.classList.add('edit-td'); // td, not textarea! the rest of rules will cascade

    var textArea = document.createElement('textarea');
    textArea.style.width = td.clientWidth + 'px';
    textArea.style.height = td.clientHeight + 'px';
    textArea.className = 'edit-area';

    textArea.value = td.innerHTML;
    td.innerHTML = '';
    td.appendChild(textArea);
    textArea.focus();

    td.insertAdjacentHTML("beforeEnd",
        '<div class="edit-controls"><button class="edit-ok">OK</button><button class="edit-cancel">CANCEL</button></div>'
    );
}

function finishTdEdit(td, isOk) {
    if (isOk) {
        td.innerHTML = td.firstChild.value;
        worker(temp.getAttribute('id'), td.innerHTML);
    } else {
        td.innerHTML = editingTd.data;
    }
    td.classList.remove('edit-td'); // remove edit class
    editingTd = null;
}