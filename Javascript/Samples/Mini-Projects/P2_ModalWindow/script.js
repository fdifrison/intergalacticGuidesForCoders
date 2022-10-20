"use strict";

// modal is the pop up window
const modal = document.querySelector(".modal");
const overlay = document.querySelector(".overlay");
const btnCloseModal = document.querySelector(".close-modal");
const btnsOpenModal = document.querySelectorAll(".show-modal");

// loop on all the buttons in btnsOpenModal and attach an event

const openModal = function () {
  modal.classList.remove("hidden");
  overlay.classList.remove("hidden");
};

const closeModal = function () {
  modal.classList.add("hidden");
  overlay.classList.add("hidden");
};

for (let i = 0; i < btnsOpenModal.length; i++) {
  btnsOpenModal[i].addEventListener("click", function () {
    // attach the same event to all the button to display the modal window
    console.log(`clicked button ${btnsOpenModal[i].textContent}`);
    // to display the modal window remove the 'hidden' class
    openModal();
  });
}

btnCloseModal.addEventListener("click", function () {
  console.log(`clicked button ${btnCloseModal.textContent}`);
  // to close the modal window add the 'hidden' class
  closeModal();
});

document.addEventListener("keydown", function (e) {
  /* console.log(e);
  console.log(typeof e.key); */
  e.key === "Escape" && !modal.classList.contains("hidden")
    ? closeModal()
    : console.log("Only Esc works");
});
