import { useEffect, useState } from "react"
import { getPaste } from "~/api/api"
import PasteItem from "~/components/PasteItem"
import type { Paste } from "~/interfaces/Paste"

export default function index() {

    const [pastes, setPastes] = useState<Paste[]>([])

    useEffect(() => {
        (async () => {
            const paste = await getPaste(1);
            setPastes([paste]);
        })();
    })

    return <div>
    {
        pastes.map(paste => {
            return <PasteItem key={paste.id} paste={paste}></PasteItem>
        })
    }

    </div>
}