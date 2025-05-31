import type { Paste } from "~/interfaces/Paste"

interface PasteProp {
    paste: Paste
}

export default function PasteItem({paste}: PasteProp) {
    return <div>
        <p>{paste.id}</p>
        <p>{paste.content}</p>
    </div>
}