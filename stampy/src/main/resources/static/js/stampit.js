console.log("Stamp js");
const pic = document.querySelector("div.post .click-target");

pic.addEventListener("mouseup", (e) => {
    const post = e.target.parentElement.parentElement;
    console.log(e.target.parentElement.id);
    console.log(post.id);
    console.log("location:", window.location.href);
//    const pic2 = e.currentTarget;
    const rect = post.getBoundingClientRect();
    const x = e.clientX - rect.left;
    const y = e.clientY - rect.top;
    const boxDimX = rect.right - rect.left;
    const boxDimY = rect.bottom - rect.top;

    let form = new FormData();
    form.append("x", Math.floor(x));
    form.append("y", Math.floor(y));
    form.append("boxDimX", Math.floor(boxDimX));
    form.append("boxDimY", Math.floor(boxDimY));
    form.append("postId", post.id);

    fetch("/post/stamp", {
        method: "POST",
        body: form,
    }).then(() => {
        // location.reload;
        window.location.href = window.location.href;
    });
});
