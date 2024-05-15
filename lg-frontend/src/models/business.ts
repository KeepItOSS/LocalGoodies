export type Business = {
    id: number,
    name: string,
    description: string,
    type: string

    phoneNumber?: string,
    email?: string,

    createdAt: Date,
    chanchedAt: Date,
    active: boolean,
}
