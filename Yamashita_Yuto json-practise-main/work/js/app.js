document.addEventListener('DOMContentLoaded', function() {
  fetch('data/product.json')
  .then(response => response.json())
  .then(products => {
	const productTable = document.getElementById('productTable');
    for (let i = 0; i < products.length; i++) {
        const product = products[i];
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.description}</td>
    `;
    productTable.appendChild(row);
    }
  })
  .catch(error => console.error('エラー:', error));
});
