'use client'

// TODO add types fetching from the server
// TODO add type for a form
// TODO add validation
export default function AddBusinessModal() {
    async function onSubmit(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault();
        console.log(event.currentTarget.elements);
    }
    return (
        <form onSubmit={onSubmit}>
            <div className="flex flex-col gap-2">
                <LabeledInput label="Name" placeholder="Name" optional={false} />
                <LabeledInput label="Description" placeholder="Description" optional={false} />
                <LabeledInput label="Phone Number" placeholder="Phone Number" optional={true} />
                <LabeledInput label="E-mail" placeholder="E-mail" optional={true} />
                <label className="form-control w-full max-w-xs">
                    <div className="label">
                        <span className="label-text">
                            Type
                            <span className="text-red-500"> * </span>
                        </span>
                    </div>
                    <select className="select select-bordered w-full max-w-xs" defaultValue={"default"}>
                        <option>Handmade Goods</option>
                        <option>Repairs</option>
                        <option>Restaurants</option>
                    </select>
                </label>
                <button type="submit" className="btn btn-primary w-full max-w-xs"> Add Business </button>
            </div>
        </form>
    );
}

type LabeledInputProps = { label: string, placeholder: string, optional: boolean };
function LabeledInput({ label, placeholder, optional }: LabeledInputProps) {
    return (
        <label className="form-control w-full max-w-xs">
            <div className="label">
                <span className="label-text">
                    { label }
                    <span className="text-red-500"> { optional ? "" : "*" } </span>
                </span>
            </div>
            <input type="text" placeholder={ placeholder } className="input input-bordered w-full max-w-xs" />
        </label>
    );
}
