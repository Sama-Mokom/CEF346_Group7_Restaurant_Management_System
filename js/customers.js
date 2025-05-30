document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('addCustomerForm');
    const tableBody = document.querySelector('#customersTable tbody');
    let customers = JSON.parse(localStorage.getItem('customers')) || [];

    function render() {
        tableBody.innerHTML = '';
        customers.forEach((c, i) => {
            tableBody.innerHTML += `
                <tr>
                    <td>${i + 1}</td>
                    <td>${c.name}</td>
                    <td>${c.contact}</td>
                    <td class="actions"><button onclick="deleteCustomer(${i})">Delete</button></td>
                </tr>`;
        });
    }

    form.onsubmit = e => {
        e.preventDefault();
        customers.push({
            name: document.getElementById('customerName').value,
            contact: document.getElementById('customerContact').value
        });
        localStorage.setItem('customers', JSON.stringify(customers));
        form.reset();
        render();
    };

    window.deleteCustomer = idx => {
        customers.splice(idx, 1);
        localStorage.setItem('customers', JSON.stringify(customers));
        render();
    };

    render();
});