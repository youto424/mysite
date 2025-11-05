// ローディング画面
const loadingScreen = document.getElementById('loading');

function showLoading() {
    loadingScreen.classList.remove('hidden');
}
function hideLoading() {
    loadingScreen.classList.add('hidden');
}
window.addEventListener('load', () => {
    setTimeout(hideLoading, 1000); 
});


// WE ARE SKILLSのアニメーション
document.addEventListener('DOMContentLoaded', () => {

  const skillLinks = document.querySelectorAll('.skill-icon a');
  
  const maxRotate = 15; 

  skillLinks.forEach(link => {

    link.addEventListener('mouseenter', () => {
      link.style.transition = 'transform 0.1s linear, box-shadow 0.1s linear';
    });

    link.addEventListener('mousemove', (e) => {
      const rect = link.getBoundingClientRect();
      const centerX = rect.left + rect.width / 2;
      const centerY = rect.top + rect.height / 2;
      const offsetX = e.clientX - centerX;
      const offsetY = e.clientY - centerY;

      const rotateY = (offsetX / (rect.width / 2)) * maxRotate;
      const rotateX = (-offsetY / (rect.height / 2)) * maxRotate;

      link.style.transform = `perspective(1200px) rotateX(${rotateX}deg) rotateY(${rotateY}deg) scale(1.08)`;
      link.style.boxShadow = '0px 20px 35px -10px rgba(0, 0, 0, 0.3)';
    });

    link.addEventListener('mouseleave', () => {
      link.style.transition = 'transform 0.4s ease-out, box-shadow 0.4s ease-out';
      
      link.style.transform = 'perspective(1200px) rotateX(0deg) rotateY(0deg) scale(1)';
      link.style.boxShadow = 'none';
    });
  });
});

// SNS icon のアニメーション（回転）
document.addEventListener('DOMContentLoaded', () => {

  const icons = document.querySelectorAll('.sns-icon i');

  icons.forEach(icon => {
    
    icon.addEventListener('mouseenter', () => {
      icon.style.transform = 'rotate(360deg)';
    });

    icon.addEventListener('mouseleave', () => {
      icon.style.transform = 'rotate(0deg)';
    });
  });
});