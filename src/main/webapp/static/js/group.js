document.addEventListener("DOMContentLoaded", () => {
  const container = document.querySelector("div.container-box");

  container?.addEventListener("click", (e) => {
    const span = e.target;
    const target = span?.closest("div.target");
    const seq = target?.dataset.seq;

    if (seq) {
      document.location.href = `${rootPath}/group/group_in/${seq}`;
    }
  });
});
