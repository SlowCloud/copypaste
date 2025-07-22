import { PasteDataParser, SyntaxHighlight, type Paste } from "~/interfaces/Paste";

// const apiUrl = "https://paste.slowcloud.xyz";
const apiUrl = "http://localhost:8080";

export async function getPastes(): Promise<Paste[]> {
    return fetch(apiUrl + "/api/paste", { method: "GET" })
    .then((res: Response) => {
        return res.json();
    })
    .then((json: Paste[]) => {
        return json.map(data => PasteDataParser.parse(data));
    });
}

export async function getPaste(pasteId: number): Promise<Paste> {
    return fetch(apiUrl + "/api/paste/" + pasteId, { method:"GET" })
    .then((res: Response) => {
        return res.json();
    })
    .then((json: Paste) => {
        return PasteDataParser.parse(json);
    })
}

export async function createPaste(paste: Omit<Paste, "id">): Promise<Paste> {
    return fetch(apiUrl + "/api/paste", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(paste)
    })
    .then((res: Response) => res.json())
    .then((json: Paste) => PasteDataParser.parse(json));
}

export async function updatePaste(pasteId: number, paste: Partial<Paste>): Promise<Paste> {
    return fetch(apiUrl + `/api/paste/${pasteId}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(paste)
    })
    .then((res: Response) => res.json())
    .then((json: Paste) => PasteDataParser.parse(json));
}

export async function deletePaste(pasteId: number): Promise<void> {
    return fetch(apiUrl + `/api/paste/${pasteId}`, {
        method: "DELETE"
    })
    .then(() => {});
}