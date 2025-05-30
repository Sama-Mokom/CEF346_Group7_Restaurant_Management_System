document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('addMenuItemForm');
    const tableBody = document.querySelector('#menuTable tbody');
    let menu = JSON.parse(localStorage.getItem('menu')) || [];

    function render() {
        tableBody.innerHTML = '';
        menu.forEach((item, i) => {
            tableBody.innerHTML += `
                <tr>
                    <td>${i + 1}</td>
                    <td>${item.name}</td>
                    <td>${item.description}</td>
                    <td>${item.price}</td>
                    <td>${item.category}</td>
                    <td class="actions"><button onclick="deleteMenuItem(${i})">Delete</button></td>
                </tr>`;
        });
    }

    if (form) {
        form.onsubmit = e => {
            e.preventDefault();
            menu.push({
                name: document.getElementById('menuItemName').value,
                description: document.getElementById('itemDescription').value,
                price: document.getElementById('menuItemPrice').value,
                category: document.getElementById('itemCategory').value
            });
            localStorage.setItem('menu', JSON.stringify(menu));
            form.reset();
            render();
        };
    }

    window.deleteMenuItem = idx => {
        menu.splice(idx, 1);
        localStorage.setItem('menu', JSON.stringify(menu));
        render();
    };

    render();
});