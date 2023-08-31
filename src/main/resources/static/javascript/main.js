/* main slide */
const mainswiper = new Swiper('.slide-wrap .swiper', {
    loop: true, 
    autoplay: {
        delay: 2000,
    },
    pagination: {
        el: '.swiper-pagination', 
        type: 'bullets', 
        clickable: true
    },
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    }
});

/* new slide */
const swiper = new Swiper('.mySwiper', {
    slidesPerView: 3,
    spaceBetween: 30,
    loop: true, 
    autoplay: {
        delay: 2000,
    },
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    }
});