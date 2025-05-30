document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('addOrderForm');
    const tableBody = document.querySelector('#ordersTable tbody');
    let orders = JSON.parse(localStorage.getItem('orders')) || [];
    // Load menu from localStorage
    let menu = JSON.parse(localStorage.getItem('menu')) || [];

    // Helper: create a map of menu items (lowercase name) to price
    function getMenuMap() {
        const map = {};
        menu.forEach(item => {
            map[item.name.trim().toLowerCase()] = parseFloat(item.price);
        });
        return map;
    }

    function render() {
        tableBody.innerHTML = '';
        orders.forEach((o, i) => {
            tableBody.innerHTML += `
                <tr>
                    <td>${i + 1}</td>
                    <td>${o.customer} (${o.customerID || ''})</td>
                    <td>${o.items}</td>
                    <td>${o.total}</td>
                    <td>${o.status}</td>
                    <td class="actions"><button onclick="deleteOrder(${i})">Delete</button></td>
                </tr>`;
        });
    }

    form.onsubmit = e => {
        e.preventDefault();
        const customer = document.getElementById('orderCustomer').value;
        const customerID = document.getElementById('customerID').value;
        const itemsInput = document.getElementById('orderItems').value;
        const itemsArr = itemsInput.split(',').map(i => i.trim().toLowerCase()).filter(i => i.length > 0);

        const menuMap = getMenuMap();
        let total = 0;
        let unavailable = [];

        itemsArr.forEach(item => {
            if (menuMap.hasOwnProperty(item)) {
                total += menuMap[item];
            } else {
                unavailable.push(item);
            }
        });

        if (unavailable.length > 0) {
            alert(`The following item(s) are not available: ${unavailable.join(', ')}`);
            return;
        }

        orders.push({
            customer: customer,
            customerID: customerID,
            items: itemsInput,
            total: total.toFixed(2),
            status: 'Pending'
        });
        localStorage.setItem('orders', JSON.stringify(orders));
        form.reset();
        render();
    };

    window.deleteOrder = idx => {
        orders.splice(idx, 1);
        localStorage.setItem('orders', JSON.stringify(orders));
        render();
    };

    render();
});