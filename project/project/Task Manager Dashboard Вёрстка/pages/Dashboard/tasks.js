document.querySelector('.search-input').addEventListener('input', function () {
  const query = this.value.trim().toLowerCase();
  // Выбираем все задачи
  const tasks = document.querySelectorAll('.task, .task-2, .task-3, .task-4, .task-5, .task-6');
  
  tasks.forEach(task => {
    // Берём текст задачи, например из .text-wrapper-5 — там "Имя задачи"
    const taskNameEl = task.querySelector('.text-wrapper-5');
    const text = taskNameEl ? taskNameEl.textContent.toLowerCase() : '';
    
    if (text.includes(query)) {
      task.style.display = '';
    } else {
      task.style.display = 'none';
    }
  });
});

document.querySelector('.wireframes-avatar').addEventListener('click', () => {
  window.location.href = 'dashboard.html';
});

  // Обработка клика по выпадающему меню "Команды"
      document.querySelectorAll(".section-2 li.has-dropdown").forEach((item) => {
        item.addEventListener("click", (e) => {
          if (e.target.closest(".submenu")) return; // если клик по подменю — не переключаем

          item.classList.toggle("active");

          // Закрываем остальные открытые меню
          document.querySelectorAll(".section-2 li.has-dropdown").forEach((other) => {
            if (other !== item && other.classList.contains("active")) {
              other.classList.remove("active");
            }
          });
        });
      });

      // Обработка клика по пунктам подменю
      document.querySelectorAll(".section-2 ul.submenu li").forEach((submenuItem) => {
        submenuItem.addEventListener("click", (e) => {
          e.stopPropagation();
          console.log("Выбрана команда:", submenuItem.textContent.trim());
          // Тут можно добавить логику подгрузки или переключения
        });
      });

      // Скрипт для кнопки "Добавить" и выпадающего меню с изображением
      const addBtn = document.getElementById('addBtn');
      const dropdownAdd = document.getElementById('dropdownAdd');

      addBtn.addEventListener('click', (e) => {
        e.stopPropagation(); // чтобы клик не дошел до window
        dropdownAdd.classList.toggle('active');
      });

      // Закрыть выпадающее меню при клике вне его
      window.addEventListener('click', () => {
        dropdownAdd.classList.remove('active');
      });
      const filtersBtn = document.getElementById('filtersBtn');
const filtersDropdown = document.getElementById('filtersDropdown');

filtersBtn.addEventListener('click', (e) => {
  e.stopPropagation();
  filtersDropdown.classList.toggle('active');
});

window.addEventListener('click', () => {
  filtersDropdown.classList.remove('active');
});


