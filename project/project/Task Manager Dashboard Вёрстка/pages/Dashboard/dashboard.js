document.addEventListener('DOMContentLoaded', function() {
  // 1. Обработчик для загрузки файла
  document.querySelector(".file-upload").addEventListener("click", () => {
    document.getElementById("fileInput").click();
  });

  // 2. Обработчик отправки формы профиля
  const profileForm = document.getElementById("profile-form");
  if (profileForm) {
    profileForm.addEventListener("submit", function(e) {
      e.preventDefault();
      console.log("Форма отправлена");
      // Здесь можно добавить логику сохранения данных формы
    });
  }

  // 3. Обработчики для вкладок (Профиль/Пароль/Уведомления)
  const tabs = document.querySelectorAll(".tabs button");
  tabs.forEach(tab => {
    tab.addEventListener("click", function() {
      tabs.forEach(t => t.classList.remove("active"));
      this.classList.add("active");
      
      // Здесь можно добавить логику переключения контента вкладок
      console.log(`Активирована вкладка: ${this.textContent}`);
    });
  });

  // 4. Обработчики для кнопок формы
  const cancelBtn = document.querySelector('.form-actions button:first-child');
  if (cancelBtn) {
    cancelBtn.addEventListener('click', function() {
      console.log('Отмена изменений');
      // Сброс значений формы
      profileForm.reset();
    });
  }

  const saveBtn = document.querySelector('.form-actions button:last-child');
  if (saveBtn) {
    saveBtn.addEventListener('click', function(e) {
      e.preventDefault();
      console.log('Сохранение изменений');
      // Здесь можно добавить AJAX-запрос для сохранения
    });
  }

  // 5. Обработчики для выпадающего меню "Команды"
  const dropdownItems = document.querySelectorAll('.sidebar-menu .has-dropdown');
  
  dropdownItems.forEach(item => {
    item.addEventListener('click', function(e) {
      if (e.target.tagName === 'LI' && e.target === this) {
        this.classList.toggle('active');
        
        // Закрываем другие открытые меню
        dropdownItems.forEach(otherItem => {
          if (otherItem !== this && otherItem.classList.contains('active')) {
            otherItem.classList.remove('active');
          }
        });
      }
    });
  });

  // 6. Обработчики для пунктов подменю команд
  const submenuItems = document.querySelectorAll('.submenu li');
  submenuItems.forEach(item => {
    item.addEventListener('click', function(e) {
      e.stopPropagation(); // Предотвращаем всплытие
      console.log('Выбрана команда:', this.textContent.trim());
      // Здесь можно добавить логику загрузки данных команды
    });
  });

  document.addEventListener("DOMContentLoaded", function () {
    const logoutImg = document.querySelector(".logout-model");
    if (logoutImg) {
      logoutImg.addEventListener("click", function () {
        console.log("Выход выполнен");
        // window.location.href = "/logout"; // или любая другая логика
      });
    }
  });
  
  

  // 7. Обработчик для загрузки аватара
  const fileInput = document.getElementById('fileInput');
  if (fileInput) {
    fileInput.addEventListener('change', function(e) {
      if (this.files && this.files[0]) {
        const reader = new FileReader();
        reader.onload = function(event) {
          document.querySelector('.avatar').src = event.target.result;
        };
        reader.readAsDataURL(this.files[0]);
        console.log('Аватар загружен:', this.files[0].name);
      }
    });
  }
});