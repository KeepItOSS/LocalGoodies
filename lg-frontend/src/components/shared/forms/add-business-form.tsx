'use client'

import { BusinessForm, BusinessType } from "@/models/business";

export default function AddBusinessForm() {
    async function onSubmit(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault();
        const formData = new FormData(event.currentTarget);
        const formFields: BusinessForm = Object.fromEntries(formData.entries()) as BusinessForm;
        console.log(formFields);
    }

    return (
        <form onSubmit={onSubmit}>
            <div className="flex flex-col gap-2">
                <LabeledInput 
                    type="text"
                    label="Name"
                    placeholder="Name"
                    name="name"
                    required={true}
                />
                <LabeledInput 
                    type="text"
                    label="Description"
                    placeholder="Description"
                    name="description"
                    required={true} />
                <LabeledInput 
                    type="text"
                    label="Phone Number"
                    placeholder="Phone Number"
                    name="phoneNumber"
                    required={false}
                />
                <LabeledInput 
                    type="email"
                    label="E-mail"
                    placeholder="E-mail"
                    name="email"
                    required={false}
                />
                <label className="form-control w-full max-w-xs">
                    <div className="label">
                        <span className="label-text">
                            Type
                            <span className="text-red-500"> * </span>
                        </span>
                    </div>
                    <select name="type" className="select select-bordered w-full max-w-xs" defaultValue={"default"} required>
                        { options.map((option, index) => {
                            return <option key={index}> { option } </option>
                        }) }
                    </select>
                </label>
                <button type="submit" className="btn btn-primary w-full max-w-xs"> Add Business </button>
            </div>
        </form>
    );
}

// TODO add fetch for type options
const options: BusinessType[] = [BusinessType.HANDMADE_GOODS, BusinessType.REPAIRS, BusinessType.RESTAURANTS];

type LabeledInputProps = { 
    label: string,
    placeholder: string,
    name: string,
    required: boolean,
    type: string
};
function LabeledInput({ label, placeholder, name, required, type }: LabeledInputProps) {
    return (
        <label className="form-control w-full max-w-xs">
            <div className="label">
                <span className="label-text">
                    { label }
                    <span className="text-red-500"> { required ? "*" : "" } </span>
                </span>
            </div>
            <input 
                name={ name }
                type={ type }
                placeholder={ placeholder }
                className="input input-bordered w-full max-w-xs"
                required = { required }
            />
        </label>
    );
}
