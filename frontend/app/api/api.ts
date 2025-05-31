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