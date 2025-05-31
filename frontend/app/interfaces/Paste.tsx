import { z } from "zod/v4"

export interface Paste {
    id: number
    content: string
    syntaxHighlight: string
};

export const SyntaxHighlight: string[] = [
    "NONE",
    "JAVA",
    "PYTHON",
] as const;

export const PasteDataParser = z.object({
    id: z.number(),
    content: z.string(),
    syntaxHighlight: z.enum(SyntaxHighlight),
});

export const PasteDataListParser = z.array(PasteDataParser);
