const targets = document.querySelectorAll('.scroll-in');
const callback = (entries, observer) => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            entry.target.classList.add('displayed');
        } else {
            entry.target.classList.remove('displayed')
        }
    });
};

const options = {
    root: null,
    rootMargin: '0px',
    threshold: 0.2
};

const observer = new IntersectionObserver(callback, options);
targets.forEach(target => {
    observer.observe(target);
});