import React, { useEffect, useState } from "react";
import { createRoot } from "react-dom/client";
import "./styles.css";

function formatUserLabel(user) {
  return `${user.email} created at ${new Date(user.createdAt).toLocaleString()}`;
}

function App() {
  const [users, setUsers] = useState([]);
  const [email, setEmail] = useState("");

  useEffect(() => {
    fetch("/api/users")
      .then((response) => response.json())
      .then((data) => setUsers(data));
  }, []);

  async function createUser(event) {
    event.preventDefault();
    const response = await fetch("/api/users", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email })
    });

    const created = await response.json();
    setUsers([...users, created]);
    setEmail("");
  }

  return (
    <main className="shell">
      <section className="toolbar">
        <div>
          <p className="eyebrow">Reviewer POC Demo</p>
          <h1>User Directory</h1>
        </div>
      </section>

      <form className="form" onSubmit={createUser}>
        <label htmlFor="email">Email</label>
        <div className="row">
          <input id="email" value={email} onChange={(event) => setEmail(event.target.value)} />
          <button type="submit">Create</button>
        </div>
      </form>

      <ul className="users">
        {users.map((user) => (
          <li key={user.id}>
            <span>{formatUserLabel(user)}</span>
            <small>{user.createdAt}</small>
          </li>
        ))}
      </ul>
    </main>
  );
}

createRoot(document.getElementById("root")).render(<App />);
