/*// show giohang
(function() {
	const giohangInfo = document.getElementById('giohang-info');
	const giohang = document.getElementById('cart');

	giohangInfo.addEventListener('click', function() {
		giohang.classList.toggle('show-cart');
	})

})();*/


// add to giohang 
(function() {

	const giohangBtn = document.querySelectorAll('.icon-shopping-cart');

	giohangBtn.forEach(function(btn) {
		btn.addEventListener("click", function(event) {


			if (event.target.classList.contains("icon-shopping-cart")) {


				let fulPath = event.target.parentElement.parentElement.parentElement.firstElementChild.src;



				let pos = fulPath.indexOf("images");


				let partPath = fulPath.slice(pos);
				console.log(partPath);

				const item = {};
				item.img = `${partPath}`;

				let name = event.target.parentElement.previousElementSibling.previousElementSibling.textContent;
				item.name = name;

				let price = event.target.parentElement.previousElementSibling.textContent;
				let finalPrice = price.slice(1).trim();
				item.price = finalPrice;

				const giohangItem = document.createElement("div");
				giohangItem.classList.add("giohang-item",
					"d-flex",
					"justify-content-between",
					"text-capitalize",
					"my-3");
				giohangItem.innerHTML = `     
               <img src="${item.img}" class="img-fluid rounded-circle" style="width:12%" id="item-img" alt="">
                <div class="item-text">    
                  <p id="giohang-item-title" class="font-weight-bold">${item.name}</p>
                  <span class="font-weight-bold mb-0" >$</span>
                  <span id="giohang-item-price" class="giohang-item-price font-weight-bold" class="mb-0">${item.price}</span>
                </div>
                <a href="#" id='giohang-item-remove' class="giohang-item-remove">
                  <i class="fas fa-trash"></i>
                </a>
              </div>
              `;

				// select giohang
				const giohang = document.getElementById("giohang");
				const total = document.querySelector(".giohang-total-container");

				giohang.insertBefore(giohangItem, total);

				showTotals();

			}

		});
	});

	//  show totals
	function showTotals() {
		const total = [];
		const items = document.querySelectorAll(".giohang-item-price");

		items.forEach(function(item) {
			total.push(parseFloat(item.textContent));
		});
		const totalMoney = total.reduce(function(total, item) {
			total += item;
			return total;
		}, 0);

		const finalMoeny = totalMoney.toFixed(2);


		document.getElementById("giohang-total").textContent = finalMoeny;

		document.getElementById("item-count").textContent = total.length;
	}


}());
