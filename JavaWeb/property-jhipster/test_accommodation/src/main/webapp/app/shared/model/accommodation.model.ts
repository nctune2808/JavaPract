import { IProperty } from 'app/shared/model/property.model';
import { IRoom } from 'app/shared/model/room.model';
import { AccommodationType } from 'app/shared/model/enumerations/accommodation-type.model';
import { AccommodationStatus } from 'app/shared/model/enumerations/accommodation-status.model';

export interface IAccommodation {
  id?: number;
  title?: string;
  type?: AccommodationType;
  status?: AccommodationStatus;
  property?: IProperty | null;
  rooms?: IRoom[];
}

export const defaultValue: Readonly<IAccommodation> = {};
