"use client";

import { useState } from "react";
import axios from "axios";

import { Button } from "@/components/ui/button";

export default function Home() {
  const [result, setResult] = useState("");

  async function testApi() {
    try {
      const { data } = await axios.get("http://localhost:8080/info");
      setResult(JSON.stringify(data, null, 2));
    } catch {
      setResult("Erro ao conectar no backend.");
    }
  }

  return (
    <main className="flex min-h-screen flex-col items-center justify-center gap-4">
      <Button onClick={testApi}>Testar API</Button>
      {result && (
        <pre className="rounded bg-muted p-4 text-sm">{result}</pre>
      )}
    </main>
  );
}
