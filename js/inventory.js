document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('addInventoryForm');
    const tableBody = document.querySelector('#inventoryTable tbody');
    let inventory = JSON.parse(localStorage.getItem('inventory')) || [];

    function render() {
        tableBody.innerHTML = '';
        inventory.forEach((item, i) => {
            tableBody.innerHTML += `
                <tr>
                    <td>${i + 1}</td>
                    <td>${item.name}</td>
                    <td>${item.quantity}</td>
                    <td>${item.unit}</td>
                    <td class="actions"><button onclick="deleteItem(${i})">Delete</button></td>
                </tr>`;
        });
    }

    form.onsubmit = e => {
        e.preventDefault();
        inventory.push({
            name: document.getElementById('itemName').value,
            quantity: document.getElementById('itemQuantity').value,
            unit: document.getElementById('itemUnit').value
        });
        localStorage.setItem('inventory', JSON.stringify(inventory));
        form.reset();
        render();
    };

    window.deleteItem = idx => {
        inventory.splice(idx, 1);
        localStorage.setItem('inventory', JSON.stringify(inventory));
        render();
    };

    render();
});