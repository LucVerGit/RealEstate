package fr.LucasVerrier.realestatemanager.database.dao

import androidx.room.*
import fr.LucasVerrier.realestatemanager.model.PointOfInterest

/**
 * Data Access Object for [PointOfInterest] entity.
 */
@Dao
interface PointOfInterestDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPointOfInterest(pointOfInterest: PointOfInterest)

    @Update
    suspend fun updatePointOfInterest(pointOfInterest: PointOfInterest)

    @Delete
    suspend fun deletePointOfInterest(pointOfInterest: PointOfInterest)

    @Query("DELETE FROM point_of_interest_table WHERE detailId = :detailId")
    suspend fun deleteAllPointsOfInterest(detailId: String)
}